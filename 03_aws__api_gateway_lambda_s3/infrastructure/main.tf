terraform {
  
  required_providers{
    aws = {
        source = "hashicorp/aws"
        version = "~> 4.31.0"
    }
  }

  required_version = ">= 1.2.8"
}

provider "aws"{
    region = "eu-west-1"
}