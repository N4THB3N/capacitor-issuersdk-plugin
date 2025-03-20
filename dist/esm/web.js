import { WebPlugin } from '@capacitor/core';
export class CapacitorIssuerSdkWeb extends WebPlugin {
    init() {
        throw new Error('Method not implemented.');
    }
    isAvailable() {
        throw new Error('Method not implemented.');
    }
    getTokens() {
        throw new Error('Method not implemented.');
    }
    containsToken(params) {
        console.log(params);
        throw new Error('Method not implemented.');
    }
    tokenize(params) {
        console.log(params);
        throw new Error('Method not implemented.');
    }
    executeProvisioning(params) {
        console.log(params);
        throw new Error('Method not implemented.');
    }
    executeProvisioningOfEncryptedCard(params) {
        console.log(params);
        throw new Error('Method not implemented.');
    }
}
//# sourceMappingURL=web.js.map