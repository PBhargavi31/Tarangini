import { Pipe, PipeTransform } from '@angular/core';
import { Plan } from '../model/plan';

@Pipe({
  name: 'speedCount'
})
export class SpeedCountPipe implements PipeTransform {

  transform(plans: Plan[], speed:number): number {
    return plans.filter(c=>c.speed==speed).length;
  }

}
