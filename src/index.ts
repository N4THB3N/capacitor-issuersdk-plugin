import { registerPlugin } from '@capacitor/core';

import type { IssuerGooglePay } from './definitions';
import {
  IContainsToken,
  IGooglePayToken,
  IProvisionByEncryptedCard,
  IProvisionByPushReceiptId,
  IProvisionInfo,
  IProvisioningResult,
  IssuerGpApi,
  ITokenizeGP,
  IUserAddress,
  Status,
  DefaultErrorApi,
  TokenStatus,
} from './definitions';

const IssuerGP = registerPlugin<IssuerGooglePay>('CapacitorIssuerGooglePay', {
  web: () => import('./web').then(m => new m.CapacitorIssuerSdkWeb()),
});

export * from './definitions';

/* interfaces & enums */
export {
  IssuerGP,
  IssuerGpApi,
  IContainsToken,
  IGooglePayToken,
  IProvisioningResult,
  IUserAddress,
  IProvisionInfo,
  IProvisionByEncryptedCard,
  IProvisionByPushReceiptId,
  ITokenizeGP,
  TokenStatus,
  DefaultErrorApi,
  Status,
};
