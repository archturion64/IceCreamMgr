import { Injectable } from "@angular/core";
import { ComponentStore, tapResponse } from "@ngrx/component-store";
import { Observable, exhaustMap, tap } from "rxjs";
import { Flavor, FlavorsService, NewFlavor } from "../core/api/v1";
import { CallState } from "./flavor-models";


interface FlavorComponentState {
    flavorsState: CallState
    flavors?: Flavor[];
    newFlavorState: CallState
}

@Injectable({
    providedIn: 'root',
  })
export class FlavorStore extends ComponentStore<FlavorComponentState>{
    private flavorsState$ = this.select((state) => state.flavorsState);
    private flavors$ = this.select((state) => state.flavors);

    newFlavorState$ = this.select((state) => state.newFlavorState);
    flavorsVm$ = this.select({
        flavorsState: this.flavorsState$,
        flavors: this.flavors$
    });

    resetNewFlavorsState() {
        this.setNewFlavorsState(CallState.INIT);
    }

    // updaters
    private setFlavorsState = this.updater((state: FlavorComponentState, flavorsState: CallState) => ({
        ...state,
        flavorsState
    }));
    private setFlavors = this.updater((state, flavors: Flavor[]) => ({
        ...state,
        flavorsState: CallState.SUCCESS,
        flavors
    }));
    private setNewFlavorsState = this.updater((state: FlavorComponentState, newFlavorState: CallState) => ({
        ...state,
        newFlavorState
    }));
    private addFlavor = this.updater((state, flavor: Flavor) => ({
        ...state,
        newFlavorState: CallState.SUCCESS,
        flavors: [flavor, ...state.flavors ?? []]
    }));


    // effects
    getFlavors = this.effect((trigger$) => trigger$.pipe(
        tap(() => {
            this.setFlavorsState(CallState.LOADING);
        }),
        exhaustMap(() => this.flavorsService.getFlavors().pipe(
            tapResponse(
                (flavors) => this.setFlavors(flavors),
                (_) => this.setFlavorsState(CallState.ERROR)
            )
        ))
    ));
    createFlavor = this.effect((newFlavor$: Observable<NewFlavor>) => newFlavor$.pipe(
        tap(() => {
            this.setNewFlavorsState(CallState.LOADING);
        }),
        exhaustMap((newFlavor) => this.flavorsService.createNewFlavor(newFlavor).pipe(
            tapResponse(
                (resId) => this.addFlavor({...newFlavor, ...resId}),
                (_) => this.setNewFlavorsState(CallState.ERROR)
            )
        ))
    ));

    constructor(private flavorsService: FlavorsService){
        super({
            flavorsState: CallState.INIT,
            flavors: undefined,
            newFlavorState: CallState.INIT,
        })
    }
}

export { CallState };
