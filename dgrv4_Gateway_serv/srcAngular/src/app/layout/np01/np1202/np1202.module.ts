import { Np1202RoutingModule } from './np1202-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PrimengModule } from 'src/app/shared/primeng.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { TokenExpiredGuard, SharedPipesModule } from 'src/app/shared';
import { Np1202Component } from './np1202.component';

@NgModule({
    imports: [
        CommonModule,
        Np1202RoutingModule,
        PrimengModule,
        SharedModule,
        SharedPipesModule,
        ReactiveFormsModule,
        FormsModule,
    ],
    declarations: [Np1202Component],
    providers: [TokenExpiredGuard]
})
export class Np1202Module { }
