import { WebPlugin } from '@capacitor/core';

import type {
  IContainsToken,
  IGooglePayToken,
  IProvisionByEncryptedCard,
  IProvisionByPushReceiptId,
  IProvisioningResult,
  ITokenizeGP,
  IssuerGooglePay,
  IssuerGpApi,
} from './definitions';

export class CapacitorIssuerSdkWeb
  extends WebPlugin
  implements IssuerGooglePay
{
  init(): Promise<IssuerGpApi<any>> {
    throw new Error('Method not implemented.');
  }
  isAvailable(): Promise<IssuerGpApi<boolean>> {
    throw new Error('Method not implemented.');
  }
  getTokens(): Promise<IssuerGpApi<IGooglePayToken>> {
    throw new Error('Method not implemented.');
  }
  containsToken(params: IContainsToken): Promise<IssuerGpApi<boolean>> {
    console.log(params);
    throw new Error('Method not implemented.');
  }
  tokenize(params: ITokenizeGP): Promise<IssuerGpApi<IProvisioningResult>> {
    console.log(params);
    throw new Error('Method not implemented.');
  }
  executeProvisioning(
    params: IProvisionByPushReceiptId,
  ): Promise<IssuerGpApi<IProvisioningResult>> {
    console.log(params);
    throw new Error('Method not implemented.');
  }
  executeProvisioningOfEncryptedCard(
    params: IProvisionByEncryptedCard,
  ): Promise<IssuerGpApi<IProvisioningResult>> {
    console.log(params);
    throw new Error('Method not implemented.');
  }
}
