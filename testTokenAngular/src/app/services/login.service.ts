import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BaseConfig } from '../config/base-config';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public static  userName = null;
  public static  nom = null;
  public static  prenom = null;
  public static  roles= [];
  public static  token = null;


  constructor(private http: HttpClient) {
  }

  login(user) {
    return this.http.post(BaseConfig.host + "/login", user, { observe: 'response' })
  }

  saveToken(token) {

    localStorage.setItem('token', token);
    LoginService.token = token;
    let jwt = new JwtHelperService();
    let userName = jwt.decodeToken(token).sub;
    let arrayN = userName.split(",");
    let roles = jwt.decodeToken(token).roles;
    roles.forEach(role => {
      LoginService.roles.push(role.authority)
    });
    LoginService.prenom = arrayN[1];
    LoginService.nom = arrayN[2];
    LoginService.userName = arrayN[0];
  }

  public logout() {
    LoginService.nom = null;
    LoginService.prenom = null;
    LoginService.token = null;
    LoginService.roles = [];
    LoginService.userName = null;
    localStorage.removeItem('token');
  }

  public isAuht(token) {
    if (token == null)
      return false;
    let jwt = new JwtHelperService();
    if (jwt.isTokenExpired(token))
      return false;
    return true;
  }

  public static getRoles(){

    let jwt = new JwtHelperService();
    let roles = jwt.decodeToken(localStorage.getItem("token")).roles;
    roles.forEach(role => {
      LoginService.roles.push(role.authority)
    });
    return LoginService.roles;
  }

  public static isAdmin(){
    let a = false;
    let jwt = new JwtHelperService();
    let roles = jwt.decodeToken(localStorage.getItem("token")).roles;
    roles.forEach(role => {
      if(role.authority=="Admin"){
        a = true;
      }
    });
    return a;
  }

  public static isUser(){
    let a = false;
    let jwt = new JwtHelperService();
    let roles = jwt.decodeToken(localStorage.getItem("token")).roles;
    roles.forEach(role => {
      if(role=="User")
        a = true;
    });
    return a;
  }

  
}
