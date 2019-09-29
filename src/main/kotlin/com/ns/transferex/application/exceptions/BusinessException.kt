package com.ns.transferex.application.exceptions

data class BusinessException(override var message: String) : RuntimeException(message)