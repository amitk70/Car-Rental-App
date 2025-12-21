import { Component, Inject } from '@angular/core';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SignuppageComponent } from '../signuppage/signuppage.component';
import { Router, RouterModule } from '@angular/router';
import { AuthenticationService } from '../../services/authentication.service';
import { DatabaseService } from '../../services/database.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-loginpage',
  imports: [AngularMaterialModule,
    ReactiveFormsModule,
    RouterModule
  ],
  templateUrl: './loginpage.component.html',
  styleUrl: './loginpage.component.css'
})
export class LoginpageComponent {

  isSpinning: boolean=false;
  loginform!:FormGroup;

  

  constructor(private formbuilder:FormBuilder,
    private auth:AuthenticationService,
    private snackbar:MatSnackBar,
    private router:Router
  ){}

  ngOnInit(){

    this.loginform = this.formbuilder.group({
      email:[null ,[Validators.required],Validators.email],
      password:[null,[Validators.required]]
    })
  }

  login(){
    console.log(this.loginform.value)
    this.auth.login(this.loginform.value).subscribe(data=>{
      console.log(data);
      if(data.userId!=null){
        const user={
          id:data.userId,
          role:data.userRole
        }
        DatabaseService.saveToken(data.jwt);
        DatabaseService.saveUser(user);
        if(DatabaseService.isAdminLoggedIn()){
          this.router.navigateByUrl("/admin/dashboard")
        }
        else{
          this.router.navigateByUrl("/customer/dashboard")
        }
      }else{
      this.snackbar.open("Something Went Wropng", "OK");
    }
    })
  }

}
