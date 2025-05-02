import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Ac0432Component } from './ac0432.component';
import { TokenExpiredGuard } from 'src/app/shared';

const routes: Routes = [
    {
        path: '', component: Ac0432Component, canActivate: [TokenExpiredGuard]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class Ac0432RoutingModule { }
