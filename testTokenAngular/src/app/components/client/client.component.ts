import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Client } from 'src/app/models/client';
import { ClientService } from 'src/app/services/client.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css'],
  providers : [NgbModal, NgbModalConfig, NgbActiveModal]
})
export class ClientComponent implements OnInit {

  clients : Client[];
  private modalref;
  form= new FormGroup({
    prenom : new FormControl("", [Validators.required]),
    nom : new FormControl("", [Validators.required])
  });

  constructor(private clientService : ClientService, private route :Router,
    private config: NgbModalConfig, private modalService: NgbModal,
    public modal: NgbActiveModal) { }

  ngOnInit(): void {
    this.clientService.listClient().subscribe(
      response=>{        
        this.clients = response
      }
    )
    this.config.backdrop = 'static';
  }

  open(content){
    if(!LoginService.isAdmin()){
      alert("Vous n'avez pas acées à ses Fonctionalités");
      return ;
    }

    this.modalref = this.modalService.open(content, { centered: true });
  }

  addClient(){

    let client = new Client();
    client.prenom = this.form.controls.prenom.value;
    client.nom = this.form.controls.nom.value;

    this.clientService.addClient(client).subscribe(
      data=>{
        alert("Client bien Ajouté");
        this.modalref.close();
        this.form.reset();
        this.clients.push(data);
      }
    )

  }

}
