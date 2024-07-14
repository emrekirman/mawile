package com.emrekirman.mawile.common.service;

import com.emrekirman.mawile.common.model.process.bill.BillResponse;
import com.emrekirman.mawile.common.model.process.credit.CreditResponse;
import com.emrekirman.mawile.common.model.process.debit.DebitResponse;

/**
 * It shows the actions they can take on the amount in the created bank assets
 *
 * @param <C> Inheritance from Credit Request
 * @param <D> Inheritance from Debit Request
 * @param <B> Inheritance from Bill Request
 */
public interface IBankOperation<C, D, B> {
    CreditResponse credit(C creditRequest);

    DebitResponse debit(D debitRequest);

    BillResponse bill(B billRequest);
}