import { TestBed } from '@angular/core/testing';

import { FlavorsResolver } from './flavors.resolver';

describe('FlavorsResolver', () => {
  let resolver: FlavorsResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(FlavorsResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
