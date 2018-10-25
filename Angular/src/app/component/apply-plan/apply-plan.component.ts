import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../service/customer.service';
import { Customer } from '../../model/customer';

@Component({
  selector: 'app-apply-plan',
  templateUrl: './apply-plan.component.html',
  styleUrls: ['./apply-plan.component.css']
})
export class ApplyPlanComponent implements OnInit {

  pTitle: string;
  dor : Date;
  customer:Customer;
  isEditing:boolean;

  constructor(private activatedRoute:ActivatedRoute,
    private customerService:CustomerService,
    private router:Router) {
     }

  ngOnInit() {
    this.customer=new Customer();
    this.dor = new Date();
    this.isEditing=false;

    this.activatedRoute.params.subscribe(
      (params)=>{
        this.pTitle = params['pTitle'];
        this.customer.pTitle = this.pTitle;
        if(this.pTitle){
          this.customerService.getCustomerBypTitle(this.pTitle).subscribe(
            (data)=>{
              this.customer=data;
              this.isEditing=true;
            }
          );
        }
      }
    );
  }

  save(){
    if(this.isEditing){
      this.customerService.updateCustomer(this.customer).subscribe(
        (data)=>{
          this.router.navigateByUrl("/?opt=u&cid="+this.customer.cid);
        },
        (error)=>{alert("This id already exist");}
      );
      console.log(this.customer.cid);
    }else{
      this.customerService.addCustomer(this.customer).subscribe(
        (data)=>{
          //this.router.navigateByUrl("/?opt=a&cid="+this.customer.cid);
          this.router.navigateByUrl("/success/?opt=a&name="+this.customer.name);
        },
        (error)=>{alert("This id already exist");}
      );
      console.log(this.customer.name);
    }
  }
}
