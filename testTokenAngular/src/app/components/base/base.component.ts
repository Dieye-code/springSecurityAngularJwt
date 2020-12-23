import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.css']
})
export class BaseComponent implements OnInit {

  date = new Date().getFullYear();
  prenom = "";
  nom = "";
  constructor(private router : Router) {
   }

  ngOnInit(): void {
    this.prenom = LoginService.prenom;
    this.nom = LoginService.nom;
  }

}
