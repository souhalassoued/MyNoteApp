import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  ngOnInit(): void {}
  constructor(private router :Router){}
  GoToUser(){
    this.router.navigate(["/user"])
  }
  dataList =[
    {name:"souha",code:128,country:"tunisia"},
    {name:"souha",code:128,country:"tunisia"},
    {name:"souha",code:128,country:"tunisia"}
  ]
}
