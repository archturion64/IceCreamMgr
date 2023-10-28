import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { FlavorFacade } from '../store/flavor-facade';
import { catchError, of, switchMap, take, tap } from 'rxjs';
import { filterUndefinedAndNull } from '../utils';


function getFlavors() {
  const flavorFacade: FlavorFacade = inject(FlavorFacade);
  return flavorFacade.flavorsVm$.pipe(
    tap(vm => {
      if(vm.flavors === undefined) {
        flavorFacade.loadFlavors();
      }
    }),
    filterUndefinedAndNull(),
    take(1)
  )
}

export const flavorGuard: CanActivateFn = () => {
  return getFlavors().pipe(
    switchMap(() => of(true)),
    catchError(() => of(false))
  );
};
