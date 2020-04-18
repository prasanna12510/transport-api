######################################################  REMOTE STATE  ##################################################
data "terraform_remote_state" "transport-service_infra_state" {
  backend = "remote"
  config = {
    organization = "terracloud-utility"
    token        = "TF_CLOUD_TOKEN"
    workspaces = {
      name = "transport-service-infra-${terraform.workspace}"
    }
  }
}


######################################################  RESOURCES  #####################################################

module "transport-api_parameters" {
  source    = "../../modules/terraform/aws/parameterstore"
  tags      = { ManagedBy = "${local.name}" }

  parameter_write = [
    {
      description = "transport-api APP NAME",
      name        = "/app/transport-api/NAME",
      overwrite   = "true",
      type        = "String",
      value       = "${var.env[terraform.workspace].APP_NAME}"
    },
    {
      description = "transport-api VPC-ID",
      name        = "/app/transport-api/VPCID",
      overwrite   = "true",
      type        = "String",
      value       = data.terraform_remote_state.transport-service_infra_state.outputs.vpc_id
    }
]
}


######################################################  OUTPUTS  #####################################################

output "transport-api_parameters" {
  value = module.transport-api_parameters.map
}
