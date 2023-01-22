resource "aws_ecr_repository" "alfa" {
  name                 = "alfa"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
}