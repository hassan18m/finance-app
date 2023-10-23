import { Transaction } from "./transaction";

export class BankAccount {
    constructor(public id: number,
        public accountNumber: string,
        public accountHolderName: string,
        public bankName: string,
        public accountType: string,
        public balance: number,
        public currency: string,
        public openDate: Date,
        public closedDate: Date,
        public status: string,
        public transactions: Transaction[]) {
    }
}
