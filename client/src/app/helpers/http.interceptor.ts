// import { Injectable } from "@angular/core";
// import {
//   HttpInterceptor,
//   HttpRequest,
//   HttpHandler,
//   HttpEvent,
//   HTTP_INTERCEPTORS,
// } from "@angular/common/http";
// import { Observable } from "rxjs";
// import { StorageService } from "../services/storage.service";

// @Injectable()
// export class HttpRequestInterceptor implements HttpInterceptor {
//   constructor(private storageService: StorageService) { }

//   intercept(
//     req: HttpRequest<any>,
//     next: HttpHandler
//   ): Observable<HttpEvent<any>> {
//     const token = this.storageService.getToken();

//     if (token) {
//       req = req.clone({
//         setHeaders: {
//           Authorization: `Bearer ${token}`,
//         },
//         withCredentials: true,
//       });
//     }

//     return next.handle(req);
//   }
// }

// export const httpInterceptorProviders = [
//   { provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true },
// ];