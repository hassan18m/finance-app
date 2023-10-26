export class UserRegisterRequest {
    constructor(public firstName: string,
        public lastName: string,
        public email: string,
        public password: string) { }
}