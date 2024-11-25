import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],

})
export class UserComponent implements OnInit{
  ngOnInit(): void {
    
  }
  constructor(private router :Router){}
  message=""
getmessage(){
  console.log("msg");
}
GoToRegister(){
  this.router.navigate(["/register"])
}
}
