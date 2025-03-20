import { registerPlugin } from '@capacitor/core';
import { Status, TokenStatus, } from './definitions';
const IssuerGP = registerPlugin('CapacitorIssuerGooglePay', {
    web: () => import('./web').then(m => new m.CapacitorIssuerSdkWeb()),
});
export * from './definitions';
/* interfaces & enums */
export { IssuerGP, TokenStatus, Status, };
//# sourceMappingURL=index.js.map