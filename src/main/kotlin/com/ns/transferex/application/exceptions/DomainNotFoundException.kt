package com.ns.transferex.application.exceptions

data class DomainNotFoundException(override var message: String) : RuntimeException(message)