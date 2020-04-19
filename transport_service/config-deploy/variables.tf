variable "availability-zones" {
  default = ["ap-southeast-1a", "ap-southeast-1b", "ap-southeast-1c"]
}

variable "env"  {
    default= {

        play= {
              APP_NAME = "transport-api"
        }
    }
}

variable "service-name" {
  description = "Tag: Service Name for all resources"
  default     = "transport-api"
}
variable "owner" {
  description = "Tag: Owner of the resources"
  default     = "transport-api"
}

variable "tag" {
  default = "APP_TAG"
}
