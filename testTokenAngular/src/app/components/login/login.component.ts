import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form= new FormGroup({
    username : new FormControl("", [Validators.required]),
    password : new FormControl("", [Validators.required])
  });
  status = 0;

  constructor(private liginService : LoginService, private routeer : Router) { 
    if(this.liginService.isAuht(localStorage.getItem("token"))){
      this.routeer.navigate(["/Client"])
      return;
    }
  }

  ngOnInit(): void { 
  }

  login(){

    let user = {
      username : this.form.controls.username.value,
      password : this.form.controls.password.value
    }

    this.liginService.login(user).subscribe(
      response =>{
        let token = response.headers.get("Authorization");
        this.liginService.saveToken(token);
        this.routeer.navigate(["/Client"])
        
      }
    );
    

  }

}
