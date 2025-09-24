import { NativeModules } from 'react-native';
import SecurePanel from './NativeSecurePanel';

export type AlypPluginType = {
  add(a: number, b: number): Promise<number>;
  multiply(a: number, b: number): Promise<number>;
  hmacSign(base64Input: string): Promise<string>;
  showSecureScreen?: () => Promise<void>;
};

const { AlypPlugin } = NativeModules as { AlypPlugin: AlypPluginType };

const api = {
  add: (a: number, b: number) => AlypPlugin.add(a, b),
  multiply: (a: number, b: number) => AlypPlugin.multiply(a, b),
  hmacSign: (b64: string) => AlypPlugin.hmacSign(b64),
  showSecureScreen: async () => {
    if (!AlypPlugin.showSecureScreen) {
      throw new Error(
        'showSecureScreen is not available. Update the native AAR/plugin.'
      );
    }
    return AlypPlugin.showSecureScreen();
  },
};

export default api;
export const { add, multiply, hmacSign } = api;
export { SecurePanel };
export const showSecureScreen = api.showSecureScreen;
