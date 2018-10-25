import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PlanService } from '../../service/plan.service';
import { Plan } from '../../model/plan';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  title: string;
  logoUrl: string;
  femaleLogo: string;
  maleLogo: string;
  othersLogo : string;
  plans: Plan[];
  cselogo:string;
  ecelogo:string;
  mechlogo : string;
  civillogo : string;
  itlogo : string;
  eeelogo:string;
  msg : string;
  field : string = "branch";
  srchValue : string;
  constructor(private planService:PlanService,
    private activatedRoute:ActivatedRoute,
    private router:Router) {
    this.title = "Tarangani Limited";
    this.femaleLogo = "/assets/Images/female.png";
    this.maleLogo = "/assets/Images/male.png";
    this.othersLogo = "/assets/Images/others.png";
    this.cselogo="/assets/Images/cse.png";
    this.ecelogo="/assets/Images/ece.png";
    this.mechlogo="/assets/Images/mech.png";
    this.civillogo="/assets/Images/civil.png";
    this.itlogo="/assets/Images/it.png";
    this.eeelogo="/assets/Images/eee.png";
  }

  ngOnInit() {
    this.planService.getAllPlans().subscribe(
      (data) => this.plans = data
    );
    this.activatedRoute.queryParams.subscribe(
      (params)=>{
        let cid=params['cid'];
        let operation=params['opt'];
        if(cid && operation){
          this.msg = "Customer # "+cid + " is successfully "+
          (operation=='d'?'Deleted':(operation=='a'?'Added':'Updated'));
          
        }else{
          this.msg=undefined;
        }
      }
    );
  }
}