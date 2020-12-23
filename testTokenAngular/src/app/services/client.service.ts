import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseConfig } from '../config/base-config';
import { Client } from '../models/client';
import { map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ClientService {


  constructor(private http : HttpClient) { }

  listClient (): Observable<Client[]>{
    return this.http.get<Client[]>(BaseConfig.host+"/api/Client",{headers : new HttpHeaders({"authorization":localStorage.getItem('token')})}).pipe(
      map(response=>response)
    )
  }

  addClient(client): Observable<Client>{
    return this.http.post<Client>(BaseConfig.host+"/api/Client/add",client,{headers : new HttpHeaders({"authorization":localStorage.getItem('token')})});
  }

}
