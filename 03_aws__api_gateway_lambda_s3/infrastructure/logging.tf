resource "aws_cloudwatch_log_group" "lambda_systems_redirection_log_group" {
  name              = "Lambda-Logs/${aws_lambda_function.systems_redirection.function_name}"
  retention_in_days = 7
  lifecycle {
    prevent_destroy = false
  }
}

resource "aws_cloudwatch_log_group" "nem_egress_api" {
  name              = "API-Gateway-Execution-Logs_${aws_api_gateway_rest_api.nem_egress_api.name}/${aws_api_gateway_stage.nem_egress_api.stage_name}"
  retention_in_days = 7
  lifecycle {
    prevent_destroy = false
  }
}
