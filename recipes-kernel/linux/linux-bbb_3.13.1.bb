# This recipe creates a patched 3.13.1 kernel for beaglebone black machines
# as detailled in Robert C. Nelsons BBB custo Linux tutorial 
# on http://ewiki.net/display/linuxonarm/BeagleBone+Black


# we are building a kernel
inherit kernel siteinfo

# we need to build a device tree
require linux-dtb.inc

# set linux version for filenames etc.
LINUX_VERSION ?= "3.13.1"

# set bbb identifier for filesnames etc.
LINUX_VERSION_EXTENSION ?= "-bbb-${LINUX_KERNEL_TYPE}"

# add patch subirectories to files search path
FILESEXTRAPATHS_prepend := "${THISDIR}/patches:${THISDIR}/patches/arm:${THISDIR}/patches/arm:${THISDIR}/patches/audio:${THISDIR}/patches/capemgr:${THISDIR}/patches/cpufreq:${THISDIR}/patches/dts:${THISDIR}/patches/fixes:${THISDIR}/patches/merge-of-kobj-min-new-20131227:${THISDIR}/patches/saucy:${THISDIR}/patches/sgx:${THISDIR}/patches/sgx-blob:${THISDIR}/patches/usb:${THISDIR}/patches/wip:"


# stable linux 3.13 kernel sources
SRC_URI = "https://www.kernel.org/pub/linux/kernel/v3.x/linux-3.13.1.tar.gz"
SRC_URI[md5sum] = "ce58e5933df2c53688d602e8a18d72c9"
SRC_URI[sha256sum] = "d461e7d6ed57e5e482eab8ccc92c389eaadd585a14cac1bcf7fd317f5a8fd7ac"

## # patches for 3.13.1 kernel (reside in ./patches)
SRC_URI += "file://0001-deb-pkg-Simplify-architecture-matching-for-cross-bui.patch \
        file://0001-arm-dts-am335x-boneblack-lcdc-add-panel-info.patch \
        file://0002-arm-dts-am335x-boneblack-add-cpu0-opp-points.patch \
        file://0003-arm-dts-am335x-bone-common-enable-and-use-i2c2.patch \
        file://0004-arm-dts-am335x-bone-common-setup-default-pinmux-http.patch \
        file://0001-pinctrl-pinctrl-single-must-be-initialized-early.patch \
        file://0001-usb-musb-musb_host-Enable-ISOCH-IN-handling-for-AM33.patch \
        file://0002-usb-musb-musb_cppi41-Make-CPPI-aware-of-high-bandwid.patch \
        file://0003-usb-musb-musb_cppi41-Handle-ISOCH-differently-and-no.patch \
        file://0001-clk-add-gpio-controlled-clock.patch \
        file://0002-ASoC-davinci-evm-Add-named-clock-reference-to-DT-bin.patch \
        file://0003-ASoC-davinci-evm-HDMI-audio-support-for-TDA998x-trou.patch \
        file://0004-ASoC-hdmi-codec-Add-devicetree-binding-with-document.patch \
        file://0005-ASoC-davinci-HDMI-audio-build-for-AM33XX-and-TDA998x.patch \
        file://0006-drm-tilcdc-Add-I2C-HDMI-audio-config-for-tda998x.patch \
        file://0001-Fix-util_is_printable_string.patch \
        file://0002-fdtdump-properly-handle-multi-string-properties.patch \
        file://0003-dtc-Dynamic-symbols-fixup-support.patch \
        file://0004-dtc-Dynamic-symbols-fixup-support-shipped.patch \
        file://0005-OF-Compile-Device-Tree-sources-with-resolve-option.patch \
        file://0006-pdev-Fix-platform-device-resource-linking.patch \
        file://0007-of-Link-platform-device-resources-properly.patch \
        file://0008-omap-Properly-handle-resources-for-omap_devices.patch \
        file://0009-arm-omap-Proper-cleanups-for-omap_device.patch \
        file://0010-staging-Platform-device-tester-Allow-removal.patch \
        file://0014-gitignore-Add-.dtbo.patch \
        file://0015-tty-omap-serial-Fix-up-platform-data-alloc.patch \
        file://0016-of-Make-device-nodes-kobjects-so-they-show-up-in-sys.patch \
        file://0017-of-selftest-Add-self-tests-for-manipulation-of-prope.patch \
        file://0018-of-remove-proc-device-tree.patch \
        file://0019-OF-kobj-node-lifecycle-fixes.patch \
        file://0020-of-i2c-Export-single-device-registration-method.patch \
        file://0021-OF-Introduce-device-tree-node-flag-helpers.patch \
        file://0022-OF-Clear-detach-flag-on-attach.patch \
        file://0023-OF-Introduce-utility-helper-functions.patch \
        file://0024-OF-Introduce-Device-Tree-resolve-support.patch \
        file://0025-OF-Introduce-DT-overlay-support.patch \
        file://0026-OF-DT-Overlay-configfs-interface.patch \
        file://0001-wip-add-capemgr-from-3.8.patch \
        file://0001-reset-Add-driver-for-gpio-controlled-reset-pins.patch \
        file://0002-prcm-port-from-ti-linux-3.12.y.patch \
        file://0003-ARM-DTS-AM335x-Add-SGX-DT-node.patch \
        file://0004-arm-Export-cache-flush-management-symbols-when-MULTI.patch \
        file://0005-hack-port-da8xx-changes-from-ti-3.12-repo.patch \
        file://0006-Revert-drm-remove-procfs-code-take-2.patch \
        file://0007-Changes-according-to-TI-for-SGX-support.patch \
        file://0001-saucy-error-variable-ilace-set-but-not-used-Werror-u.patch \
        file://0002-saucy-disable-Werror-pointer-sign.patch \
        file://0003-saucy-disable-stack-protector.patch \
"

# default config file for kernel (resides in ./patches), exchange if you want a customized kernel
# the default config builds practically everything, except total fringe scenarios like ham radio
SRC_URI += "file://defconfig"


SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

# directory in which to apply the patches
S = "${WORKDIR}/linux-3.13.1"
#S = "${WORKDIR}/${PN}-${PV}"
            

