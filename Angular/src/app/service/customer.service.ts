import { Injectable } from '@angular/core';
import { Customer } from '../model/customer';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  baseUrl: string;
  constructor(private http: Http) { 
    this.baseUrl = "http://localhost:2020/customers";
  }

  getBaseUrlByName(name: string): string {
    return this.baseUrl + "/" + name;
  }

  getBaseUrlBypTitle(pTitle: string): string {
    return this.baseUrl + "/" + pTitle;
  }

  getCustomerByName(name: string): Observable<Customer> {
    return this.http.get(this.getBaseUrlByName(name)).pipe(
      map(data => data.json())
    );
  }

  getCustomerBypTitle(pTitle: string): Observable<Customer> {
    return this.http.get(this.getBaseUrlBypTitle(pTitle)).pipe(
      map(data => data.json())
    );
  }

  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return new RequestOptions({ headers: headers });
  }

  addCustomer(customer: Customer): Observable<Customer> {
    return this.http.post(this.baseUrl, JSON.stringify(customer), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }

  updateCustomer(customer: Customer): Observable<Customer> {
    return this.http.put(this.baseUrl, JSON.stringify(customer), this.getJsonContentTypeHeader()).pipe(
      map(data => data.json())
    );
  }
}
