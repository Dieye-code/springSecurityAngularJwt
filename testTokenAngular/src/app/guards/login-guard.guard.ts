import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from '../services/login.service';

@Injectable({
  providedIn: 'root'
})
export class LogindGuard implements CanActivate {

  constructor(private router : Router, private loginService : LoginService){}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):  boolean {

      if(!this.loginService.isAuht(localStorage.getItem("token")))
        this.router.navigate(["/Login"]);
    return true;
  }
  
}
