import { Pipe, PipeTransform } from '@angular/core';
import { Plan } from '../model/plan';

@Pipe({
  name: 'usageCount'
})
export class UsageCountPipe implements PipeTransform {

  transform(students:Plan[],maxUsage:number): number {
    return students.filter(c=>c.maxUsage==maxUsage).length
  }

}
