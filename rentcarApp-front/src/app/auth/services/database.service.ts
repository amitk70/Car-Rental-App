import { Injectable } from '@angular/core';

const USER = "user";
const TOKEN = "token";

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {


  constructor() { }

  static saveToken(token:string):void{
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token)
  }

  static saveUser(user: any):void{
    window.localStorage.removeItem(USER);
    window.localStorage.setItem(USER,JSON.stringify(user))
  }


  static getUser(){
    return JSON.parse(window.localStorage.getItem(USER) as string);
  }

  static getToken(){
    return window.localStorage.getItem(TOKEN);
  }

  static getUserRole(): string{
    const user = this.getUser();
    if(user == null) return "";
    return user.role;
  }

  static isAdminLoggedIn(): boolean{
    if(this.getToken()==null)
      return false;
    const role : string=this.getUserRole();
    return role== 'ADMIN'
  }
  static isCustomerLoggedIn(): boolean{
    if(this.getToken()==null)
      return false;
    const role : string=this.getUserRole();
    return role== 'CUSTOMER'
  }

}
