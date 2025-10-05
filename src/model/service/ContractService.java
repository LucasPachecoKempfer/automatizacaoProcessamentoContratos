package model.service;

import model.entites.Contract;
import model.entites.Installment;

import java.time.LocalDate;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public OnlinePaymentService getOnlinePaymentService() {
        return onlinePaymentService;
    }

    public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months){

        Double baseAmount = contract.getTotalValue() / months;

        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);

            Double updatedAmount = onlinePaymentService.interest(baseAmount, i);
            updatedAmount = onlinePaymentService.paymentFee(updatedAmount);

            Installment installment = new Installment(dueDate, updatedAmount);
            contract.addInstallment(installment);
        }

    }
}
