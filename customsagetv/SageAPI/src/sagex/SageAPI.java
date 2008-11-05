package sagex;

import sagex.remote.EmbeddedSageAPIProvider;
import sagex.remote.SageAPIRemote;
import sagex.remote.server.ServerInfo;

public class SageAPI {
	private static ISageAPIProvider provider = null;
	
	private static ThreadLocal<String> uiContext = new ThreadLocal<String>();

	public static ISageAPIProvider getProvider() {
		if (provider == null) {
			// to find the provider to use
			try {
				System.out.println("SageAPI Provider is not set, will try to find the server...");
				ServerInfo info = SageAPIRemote.findRemoteServer(5000);
				setProvider(new SageAPIRemote(info.baseServerUrl));
			} catch (Throwable t) {
				setProvider(new EmbeddedSageAPIProvider());
			}
		}
		return provider;
	}

	public static void setProvider(ISageAPIProvider provider) {
		System.out.println("Sage Provider Implementation: " + provider.getClass().getName() + "; " + provider.toString());
		SageAPI.provider = provider;
	}

	public static Object call(String serviceName, Object[] args) {
		String ctx = getUIContext();
		if (ctx!=null) {
			return call(ctx, serviceName, args);
		} else {
			return SageAPI.getProvider().callService(serviceName, args);
		}
	}

	public static Object call(String context, String serviceName, Object[] args) {
		System.out.println("Using Context: " + context + " for command: " + serviceName);
		return SageAPI.getProvider().callService(context, serviceName, args);
	}
	
	/**
	 * set the UI context for the current thread.  All calls within this thread will now use the provided context.
	 * 
	 * @deprecated may not be safe when embedded in a sage tv container
	 * @param context
	 */
	public static void setUIContext(String context) {
		uiContext.set(context);
	}

	/**
	 * returns the currently set UI context for this thread.
	 * 
	 * @return ui context for this thread.
	 */
	public static String getUIContext() {
		return uiContext.get();
	}
}