import { Component, OnInit } from '@angular/core';
import { PlanService } from '../../service/plan.service';
import { ActivatedRoute } from '@angular/router';
import { Plan } from '../../model/plan';

@Component({
  selector: 'app-plan-list',
  templateUrl: './plan-list.component.html',
  styleUrls: ['./plan-list.component.css']
})
export class PlanListComponent implements OnInit {
  
  plans : Plan[];
  maleLogo : string;
  femaleLogo : string;
  othersLogo : string;
  cselogo : string;
  ecelogo : string;
  mechlogo : string;
  civillogo : string;
  itlogo : string;
  eeelogo:string;
  sortby:string;
  isDesc:boolean=false;
  code:string;
  msg : string;

  constructor(
    private planService : PlanService, 
    private activatedRoute : ActivatedRoute) 
    {
      this.maleLogo="assets/Images/male.png";
      this.femaleLogo="assets/Images/female.png";
      this.othersLogo="assets/Images/others.png";
      this.cselogo="/assets/Images/cse.png";
      this.ecelogo="/assets/Images/ece.png";
      this.mechlogo="/assets/Images/mech.png";
      this.civillogo="/assets/Images/civil.png";
      this.itlogo="/assets/Images/it.png";
      this.eeelogo="/assets/Images/eee.png";
   }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(
      (params) =>{
        let field = params['field'];
        let srchValue = params['srchValue'];
        let value = params['value'];

        console.log(field +" : "+srchValue);
        console.log(field +" : "+value);

        if(field && srchValue){
          this.planService.searchPlans(field,srchValue).subscribe(
            (data) => this.plans=data,
            (err)=>this.plans=undefined
          );
        }else{
          this.planService.getAllPlans().subscribe(
            (data) => this.plans=data,
            (err)=>this.plans=undefined
          );
        }
      }
    );
    this.activatedRoute.params.subscribe(
      (params) => {
        let sf = params['field'];
        let sv = params['value']
        if (sf && sv) {
          this.planService.searchPlans(sf,sv).subscribe(
            (data) => this.plans=data,
            (err)=>this.plans=undefined
          );
        }
      }
    );
  }
  order() {
    if(this.isDesc)
      this.isDesc=false;
    else
      this.isDesc=true;
  }
}