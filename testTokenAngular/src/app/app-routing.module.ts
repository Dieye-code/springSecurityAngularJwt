import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BaseComponent } from './components/base/base.component';
import { ClientComponent } from './components/client/client.component';
import { CompteComponent } from './components/compte/compte.component';
import { LoginComponent } from './components/login/login.component';
import { RoleComponent } from './components/role/role.component';
import { UserComponent } from './components/user/user.component';
import { LogindGuard } from './guards/login-guard.guard';


const routes: Routes = [
  {
    path : "", 
    canActivate : [LogindGuard],
    component : BaseComponent,
    children : [
      {
        path : "Client",
        component : ClientComponent
      },
      {
        path : "Compte",
        component : CompteComponent
      },
      {
        path : "User",
        component : UserComponent
      },
      {
        path : "Role",
        component : RoleComponent
      }
    ]
  },
  {
    path : "Login",
    component : LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
