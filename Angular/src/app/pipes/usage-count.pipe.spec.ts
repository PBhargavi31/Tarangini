import { UsageCountPipe } from './usage-count.pipe';

describe('UsageCountPipe', () => {
  it('create an instance', () => {
    const pipe = new UsageCountPipe();
    expect(pipe).toBeTruthy();
  });
});
