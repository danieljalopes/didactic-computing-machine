output "lambda__systems_redirection__arn"{
    value = aws_lambda_function.systems_redirection.arn
    description = "Lambda systems_redirection arn"
}

output "apigw__nem_egress_api__url" {
  value = aws_api_gateway_deployment.nem_egress_api.invoke_url
  description ="APIGW nem_egress URL"
}

output "region"{
  value = data.aws_region.current
  description = "Region"
}