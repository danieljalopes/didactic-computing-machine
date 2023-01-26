



/*
Template file for API GW
*/
data "template_file" "api_gw_config_file" {
  template = file("${path.module}/files/api_gw.yaml")

  vars = {
    get_lambda_arn = "${aws_lambda_function.systems_redirection.arn}"
    get_lambda_execution_arn = "${aws_iam_policy.nem_egress__api_gw.arn}"
  }

}
/**
Create AWS API GATEWAY
*/
resource "aws_api_gateway_rest_api" "nem_egress_api" {
  name = "nem-egress-api"

  body = "${data.template_file.api_gw_config_file.rendered}"

  endpoint_configuration {
    types = ["EDGE"]
  }
}

#Integrate API Gateway with Lambda
resource "aws_lambda_permission" "apigw" {
	action        = "lambda:InvokeFunction"
	function_name = aws_lambda_function.systems_redirection.arn
	principal     = "apigateway.amazonaws.com"

	source_arn = "${aws_api_gateway_rest_api.nem_egress_api.execution_arn}/*/*"
}

#Create API Stage
resource "aws_api_gateway_stage" "nem_egress_api" {
  deployment_id = aws_api_gateway_deployment.nem_egress_api.id
  rest_api_id   = aws_api_gateway_rest_api.nem_egress_api.id
  stage_name    = "production"
  
}

#Deploy API Gateway do Stage
resource "aws_api_gateway_deployment" "nem_egress_api" {
  rest_api_id = aws_api_gateway_rest_api.nem_egress_api.id

  triggers = {
    redeployment = sha1(jsonencode(aws_api_gateway_rest_api.nem_egress_api.body))
  }

  lifecycle {
    create_before_destroy = true
  }
}


resource "aws_api_gateway_method_settings" "general_settings" {
  rest_api_id = "${aws_api_gateway_rest_api.nem_egress_api.id}"
  stage_name  = "${aws_api_gateway_stage.nem_egress_api.stage_name}"
  method_path = "*/*"

  settings {
    # Enable CloudWatch logging and metrics
    metrics_enabled        = true
    data_trace_enabled     = true
    logging_level          = "INFO"

    # Limit the rate of calls to prevent abuse and unwanted charges
    throttling_rate_limit  = 100
    throttling_burst_limit = 50
  }
}

