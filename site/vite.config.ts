import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  base: "/design-patterns/",
  build: {
    outDir: "dist",
    sourcemap: false,
  },
  plugins: [react()],
});
