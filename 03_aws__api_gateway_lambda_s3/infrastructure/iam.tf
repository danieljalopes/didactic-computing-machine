## -------------------------------------------------
## IAM
## Define ROLES and POLICIES
## -------------------------------------------------

#START::Lambda::lambda_systems_redirection

#Execution Role the lambda will use
resource "aws_iam_role" "lambda_systems_redirection" {
  name = "lambda_systems_redirection_role"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

#Create document for policy for lambda
data "aws_iam_policy_document" "lambda__systems_redirection"{

  statement {
    sid = "AllowToCreateLogStream"
    actions   = [ "logs:CreateLogGroup",
        "logs:CreateLogStream",
        "logs:DescribeLogGroups",
        "logs:DescribeLogStreams",
        "logs:PutLogEvents",
        "logs:GetLogEvents",
        "logs:FilterLogEvents"]
    resources = ["arn:aws:logs:*"]
    effect = "Allow"
  }

  statement {
    sid = "AllowListS3"
    actions   = ["s3:ListBucket"]
    resources = [aws_s3_bucket.car-data-repository.arn]
    effect = "Allow"

  }

  statement {
    sid = "AllowWriteToS3"
    actions   = ["s3:PutObject"]
    resources = ["${aws_s3_bucket.car-data-repository.arn}/*"] 
    effect = "Allow"

  }
}

#Generate the Policie in AWS format
resource "aws_iam_policy" "lambda__systems_redirection" {
  name        = "lambda__systems_redirection"
  path        = "/"
  description = "AWS IAM Policy for Lamda systems_redirection"
  policy      = data.aws_iam_policy_document.lambda__systems_redirection.json
}

#Attatch the Policy to Role
resource "aws_iam_role_policy_attachment" "attach_lambda_systems_redirection" {
  role       = aws_iam_role.lambda_systems_redirection.name
  policy_arn = aws_iam_policy.lambda__systems_redirection.arn
}

#END::Lambda::lambda_systems_redirection


#START::API GATEWAY

resource "aws_iam_role" "nem_egress_api_gateway_exec_role" {
  name = "nem_egress_api_gateway_exec_role"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "",
      "Effect": "Allow",
      "Principal": {
        "Service": "apigateway.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
EOF
}


#Execution Policy for API GW 
data "aws_iam_policy_document" "nem_egress__api_gw"{

  statement {
    sid = "AllowToCreateLogStream"
    actions   = [ "logs:CreateLogGroup",
        "logs:CreateLogStream",
        "logs:DescribeLogGroups",
        "logs:DescribeLogStreams",
        "logs:PutLogEvents",
        "logs:GetLogEvents",
        "logs:FilterLogEvents"]
    resources = ["arn:aws:logs:*"]
    effect = "Allow"
  }

  statement {
    sid = "GetLambdaFunction"
    actions   = ["lambda:ListFunctions"]
    resources = [aws_lambda_function.systems_redirection.arn]
    effect = "Allow"

  }

  statement {
    sid = "AllowToUseLambdaFunction"
    actions   = ["lambda:InvokeFunction",
                "lambda:GetFunction"]
    resources = [aws_lambda_function.systems_redirection.arn]
    effect = "Allow"

  }


}

#Generate the Policie in AWS format
resource "aws_iam_policy" "nem_egress__api_gw" {
  name        = "nem_egress__api_gw"
  path        = "/"
  description = "AWS IAM Policy to execute Lambda  "
  policy      = data.aws_iam_policy_document.nem_egress__api_gw.json
}

#Associate a Policie to a Role
resource "aws_iam_role_policy_attachment" "attach_nem_egress_api" {
  role       = aws_iam_role.nem_egress_api_gateway_exec_role.name
  policy_arn = aws_iam_policy.nem_egress__api_gw.arn
}

#END:: API GATEWAY

