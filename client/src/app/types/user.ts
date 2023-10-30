import { BankAccount } from "./bank-account";
import { Budget } from "./budget";

export class User {
    constructor(public id: string,
        public firstName: string,
        public lastName: string,
        public email: string,
        public bankAccounts: BankAccount[],
        public budgets: Budget[]) { }
}