import { Injectable } from '@angular/core';
import { FlavorStore } from './flavor-store';
import { NewFlavor } from '../core/api/v1';

@Injectable({
  providedIn: 'root'
})
export class FlavorFacade {

  readonly flavorsVm$ = this.store.flavorsVm$;
  readonly newFlavorState$ = this.store.newFlavorState$;

  constructor(private store: FlavorStore) {
  }

  loadFlavors() {
    this.store.getFlavors();
  }

  addFlavor(newFlavor: NewFlavor) {
    this.store.createFlavor(newFlavor);
  }

  resetNewFlavorsState() {
    this.store.resetNewFlavorsState();
  }
}


