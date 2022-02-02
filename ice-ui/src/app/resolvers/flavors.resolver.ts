import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { catchError, map, Observable, of } from 'rxjs';
import { ErrorService } from '../services/error.service';
import { FlavorsService } from '../services/flavors.service';

@Injectable({
  providedIn: 'root'
})
export class FlavorsResolver implements Resolve<Observable<any>> {

  constructor(private flavorService: FlavorsService,
              private errorService: ErrorService){}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any>|Promise<any> {
    return this.flavorService.getFlavors().pipe(
      map(res => res),
      catchError(error => {
        return this.errorService.handleConnectionError(error);
      })
    );
  }
}
