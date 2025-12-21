import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_URL = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private Http:HttpClient) { }

  registerCustomer(signuprequest:any):Observable<any>{
    return this.Http.post<any>(BASE_URL+`/api/auth/signup`,signuprequest)
  }

  login(loginRquest:any):Observable<any>{
    return this.Http.post<any>(BASE_URL+`/api/auth/login`,loginRquest)
  }
}
