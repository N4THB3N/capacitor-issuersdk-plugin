import { WebPlugin } from '@capacitor/core';
import type { IContainsToken, IGooglePayToken, IProvisionByEncryptedCard, IProvisionByPushReceiptId, IProvisioningResult, ITokenizeGP, IssuerGooglePay, IssuerGpApi } from './definitions';
export declare class CapacitorIssuerSdkWeb extends WebPlugin implements IssuerGooglePay {
    init(): Promise<IssuerGpApi<any>>;
    isAvailable(): Promise<IssuerGpApi<boolean>>;
    getTokens(): Promise<IssuerGpApi<IGooglePayToken>>;
    containsToken(params: IContainsToken): Promise<IssuerGpApi<boolean>>;
    tokenize(params: ITokenizeGP): Promise<IssuerGpApi<IProvisioningResult>>;
    executeProvisioning(params: IProvisionByPushReceiptId): Promise<IssuerGpApi<IProvisioningResult>>;
    executeProvisioningOfEncryptedCard(params: IProvisionByEncryptedCard): Promise<IssuerGpApi<IProvisioningResult>>;
}
