package com.ns.transferex.application.service

import com.ns.transferex.domain.Transaction

interface TransferService {
    fun applyTransfer(transaction: Transaction)
}