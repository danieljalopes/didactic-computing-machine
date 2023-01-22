resource "aws_lambda_function" "systems_redirection" {
  # If the file is not in the current working directory you will need to include a
  # path.module in the filename.
  filename         = "${path.module}/files/lambda_code.zip"
  source_code_hash = "${data.archive_file.zip_the_python_code.output_base64sha256}"
  function_name    = "systems_redirection"
  role             = aws_iam_role.lambda_systems_redirection.arn
  handler          = "lambda_code.lambda_handler"

  runtime = "python3.8"
  depends_on = [
    aws_iam_role_policy_attachment.attach_lambda_systems_redirection
  ]

  environment {
    variables = {
      S3_BUCKET = "${aws_s3_bucket.car-data-repository.bucket}"
    }
  }
}

data "archive_file" "zip_the_python_code" {
  type        = "zip"
  source_dir  = "${path.module}/files/"
  output_path = "${path.module}/files/lambda_code.zip"

}
