import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginpageComponent } from './auth/component/loginpage/loginpage.component';
import { SignuppageComponent } from './auth/component/signuppage/signuppage.component';

const routes: Routes = [

  {path:'login', component:LoginpageComponent},
  {path:'signup', component:SignuppageComponent},
  {path:'admin', loadChildren:()=> import("./modules/admin/admin.module").then(m=>m.AdminModule)},
  {path:'customer', loadChildren:()=> import("./modules/customer/customer.module").then(m=>m.CustomerModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
