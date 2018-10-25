import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../service/customer.service';
import { ActivatedRoute } from '@angular/router';
import { Customer } from '../../model/customer';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css']
})
export class SuccessComponent implements OnInit {
  customer : Customer;
  constructor(private customerService: CustomerService,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(
      (params) => {
        let name = params['name'];
        let opt = params['opt'];
        if (name && opt=='a') {
          this.customerService.getCustomerByName(name).subscribe(
            (data) => this.customer = data
          );
        }
      }
    );
    console.log(this.customer.name);
  }

}
