import { Component } from '@angular/core';
import { AngularMaterialModule } from '../../../AngularMaterialModule';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthenticationService } from '../../services/authentication.service';

@Component({
  selector: 'app-signuppage',
  imports: [AngularMaterialModule,
    ReactiveFormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './signuppage.component.html',
  styleUrl: './signuppage.component.css'
})
export class SignuppageComponent {

    isSpinning: boolean=false;
    signupform!:FormGroup;
  
    constructor(private formbuilder:FormBuilder,
      private authservice:AuthenticationService
    ){}
  
    ngOnInit(){
  
      this.signupform = this.formbuilder.group({
        email:[null ,[Validators.required],Validators.email],
        name:[null, [Validators.required]],
        password:[null,[Validators.required]],
        confirmpassword:[null, [Validators.required , this.confirmpasswordvalidation]]
      })
    }


    confirmpasswordvalidation = (control : FormControl): { [s:string]:boolean } => {
      if(!control.value){
        return {required: true}
      }else if(control.value !== this.signupform.controls['password'].value){
        return { confirm: true , error:true}
      }
        return {};
      }

    register(){
      console.log(this.signupform.value)
      this.authservice.registerCustomer(this.signupform.value).subscribe(data=>{
        console.log(data)
      })
    }


}
