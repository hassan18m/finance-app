export class User {
    constructor(public id: string,
        public firstName: string,
        public lastName: string,
        public email: string) { }
}

export interface UserData {
    id: string;
    firstName: string;
    lastName: string;
    email: string;
}